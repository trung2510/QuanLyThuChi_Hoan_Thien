package com.example.quanlythuchi.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlythuchi.R;
import com.example.quanlythuchi.adapter.KhoanThuAdapter;
import com.example.quanlythuchi.dao.KhoanThuDAO;
import com.example.quanlythuchi.model.Note;

import java.util.List;

public class FragmentKhoanThu extends Fragment {
    protected RecyclerView ryView;
    protected List<Note> noteList;
    protected KhoanThuAdapter adapter;
    KhoanThuDAO khoanThuDAO;
    String id ="";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.khoanthu, container, false);

        ryView = view.findViewById(R.id.rvThuChi);
        FloatingActionButton fab = view.findViewById(R.id.fab);

        khoanThuDAO = new KhoanThuDAO(getActivity());

//
//        noteList.add(new Note("Lãi ngân hàng",10.000));
//        noteList.add(new Note("Lương",10.000));
//        noteList.add(new Note("Bán hàng Online",10.000));
//        noteList.add(new Note("Đòi nợ thuê",10.000));
//        noteList.add(new Note("Bố mẹ cho",10.000));
//        noteList.add(new Note("Đánh lô đề",10.000));

        noteList = khoanThuDAO.getAllNote();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        ryView.setLayoutManager(layoutManager);
        adapter = new KhoanThuAdapter(getActivity(), noteList);
        ryView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                LayoutInflater inflater = getLayoutInflater();
                final View view = inflater.inflate(R.layout.alert_layout, null);
                builder.setView(view);
                builder.setCancelable(false);



                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {

                    EditText edtThem = (EditText) view.findViewById(R.id.edtThem);
//                    EditText  edtGia = (EditText) view.findViewById(R.id.edtGia);

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Note note = new Note(null,edtThem.getText().toString());
                        if (khoanThuDAO.insertNote(note)==1){
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                            noteList = khoanThuDAO.getAllNote();

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            ryView.setLayoutManager(layoutManager);
                            adapter = new KhoanThuAdapter(getActivity(), noteList);
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
