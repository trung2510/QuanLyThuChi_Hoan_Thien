package com.example.quanlythuchi.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythuchi.R;
import com.example.quanlythuchi.dao.KhoanChiDAO;
import com.example.quanlythuchi.dao.KhoanThuDAO;
import com.example.quanlythuchi.dao.LoaiChiDAO;
import com.example.quanlythuchi.model.Note;
import com.example.quanlythuchi.model.Note2;
import com.example.quanlythuchi.model.Note4;

import java.util.ArrayList;
import java.util.List;

public class LoaiChiAdapter extends RecyclerView.Adapter<LoaiChiAdapter.ViewHolder> {
    Context context;
    List<Note4> noteList;
    LoaiChiDAO khoanThuDAO;
    String id="";
    List<String> stringListMaKH = new ArrayList<>();

    public LoaiChiAdapter(Context context, List<Note4> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_loai,viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.tvKhoan.setText(noteList.get(i).getNoteTitle4());
        viewHolder.tvChi.setText(noteList.get(i).getNoteContent4());
        viewHolder.tvGia.setText(noteList.get(i).getGia4()+" ");


        viewHolder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final View view = LayoutInflater.from(context).inflate(R.layout.alert_edit_layout_loai, null);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);


                builder.setView(view);
                builder.setCancelable(false);




                final AppCompatSpinner spnKhoan = (AppCompatSpinner) view.findViewById(R.id.spnKhoan);
                final EditText edtLoai = (EditText) view.findViewById(R.id.edtLoai);
                final EditText edtGia = (EditText) view.findViewById(R.id.edtGia);

                KhoanChiDAO khachhangDAO = new KhoanChiDAO(context);
                stringListMaKH = khachhangDAO.getAllNoteTitle();
                ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item,stringListMaKH);
                spnKhoan.setAdapter(arrayAdapter);


                spnKhoan.setSelection(spnKhoan.getSelectedItemPosition());
                edtLoai.setText(noteList.get(i).getNoteContent4());
                edtGia.setText(noteList.get(i).getGia4()+"");

                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        khoanThuDAO = new LoaiChiDAO(context);

                        //Em nhớ sửa id nhé  ...vì lúc đầu em khởi tạo id là rỗng nên id đâu
                        //mà updte
                        id = noteList.get(i).getNoteId4();


                        Note4 note = new Note4(id, spnKhoan.getSelectedItem().toString(),edtLoai.getText().toString(), Double.parseDouble(edtGia.getText().toString()));

                        if (khoanThuDAO.updateNote(note) == 1) {

                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_LONG).show();
                            viewHolder.tvKhoan.setText(spnKhoan.getSelectedItem().toString());
                            viewHolder.tvChi.setText(edtLoai.getText().toString());
                            viewHolder.tvGia.setText(edtGia.getText().toString());
                            noteList.set(i, note);
                            notifyItemChanged(i);

                        } else {
                            Toast.makeText(context, "Sửa không thành công", Toast.LENGTH_SHORT).show();
                        }
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
        viewHolder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanThuDAO = new LoaiChiDAO(context);
                khoanThuDAO.deleteNote(noteList.get(i));
                noteList.remove(i);
                notifyDataSetChanged();
            }
        });


    }


    @Override
    public int getItemCount() {
        if (noteList == null) return 0;
        else
            return noteList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKhoan;
        private TextView tvChi;
        private TextView tvGia;
        private ImageView imgedit;
        private ImageView imgdelete;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvKhoan = (TextView) itemView.findViewById(R.id.tvKhoan);
            tvChi = (TextView) itemView.findViewById(R.id.tvChi);
            tvGia = (TextView) itemView.findViewById(R.id.tvGia);
            imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
            imgdelete = (ImageView) itemView.findViewById(R.id.imgdelete);

        }
    }
}
