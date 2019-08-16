package com.example.quanlythuchi.fragment;

import android.R.layout;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlythuchi.R;
import com.example.quanlythuchi.adapter.KhoanChiAdapter;
import com.example.quanlythuchi.adapter.KhoanThuAdapter;
import com.example.quanlythuchi.adapter.LoaiChiAdapter;
import com.example.quanlythuchi.dao.KhoanChiDAO;
import com.example.quanlythuchi.dao.LoaiChiDAO;
import com.example.quanlythuchi.model.Note4;
import com.example.quanlythuchi.model.Note4;

import java.util.ArrayList;
import java.util.List;

public class FragmentLoaiChi extends Fragment {

    protected RecyclerView ryView;
    protected List<Note4> noteList;
    protected LoaiChiAdapter adapter;
    LoaiChiDAO khoanChiDAO;
    String id ="";
    List<String> stringListMaKH = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.loaichi, container, false);

        ryView = view.findViewById(R.id.rvThuChi);
        FloatingActionButton fab = view.findViewById(R.id.fab);

        khoanChiDAO = new LoaiChiDAO(getActivity());


//
//        noteList.add(new Note("Lãi ngân hàng",10.000));
//        noteList.add(new Note("Lương",10.000));
//        noteList.add(new Note("Bán hàng Online",10.000));
//        noteList.add(new Note("Đòi nợ thuê",10.000));
//        noteList.add(new Note("Bố mẹ cho",10.000));
//        noteList.add(new Note("Đánh lô đề",10.000));

        noteList = khoanChiDAO.getAllNote();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        ryView.setLayoutManager(layoutManager);
        adapter = new LoaiChiAdapter(getActivity(), noteList);
        ryView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = getLayoutInflater();
                final View view = inflater.inflate(R.layout.alert_layout_loai, null);
                builder.setView(view);
                builder.setCancelable(false);

                final AppCompatSpinner spnKhoan = (AppCompatSpinner) view.findViewById(R.id.spnKhoan);
                final EditText edtThem = (EditText) view.findViewById(R.id.edtThem);
                final EditText edtGia = (EditText) view.findViewById(R.id.edtGia);

                KhoanChiDAO khachhangDAO = new KhoanChiDAO(getActivity());
                stringListMaKH = khachhangDAO.getAllNoteTitle();
                ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), layout.simple_spinner_dropdown_item,stringListMaKH);
                spnKhoan.setAdapter(arrayAdapter);



                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Note4 note = new Note4(null,spnKhoan.getSelectedItem().toString(),edtThem.getText().toString(),Double.parseDouble(edtGia.getText().toString()));
                        if (khoanChiDAO.insertNote(note)==1){
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                            noteList = khoanChiDAO.getAllNote();

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            ryView.setLayoutManager(layoutManager);
                            adapter = new LoaiChiAdapter(getActivity(), noteList);
                            ryView.setAdapter(adapter);
                        }
                        else {
                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }

                        adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


                builder.show();


            }
        });


        return view;
    }
}
