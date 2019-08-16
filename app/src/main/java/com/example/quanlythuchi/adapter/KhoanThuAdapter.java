package com.example.quanlythuchi.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythuchi.R;
import com.example.quanlythuchi.dao.KhoanThuDAO;
import com.example.quanlythuchi.model.Note;

import java.util.List;

public class KhoanThuAdapter extends RecyclerView.Adapter<KhoanThuAdapter.ViewHolder> {
    Context context;
    List<Note> noteList;
    KhoanThuDAO khoanThuDAO;
    String id="";

    public KhoanThuAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.tvName.setText(noteList.get(i).getNoteTitle());



        viewHolder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final View view = LayoutInflater.from(context).inflate(R.layout.alert_edit_layout, null);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);


                builder.setView(view);
                builder.setCancelable(false);

                final EditText edtTen = (EditText) view.findViewById(R.id.edtTen);


                edtTen.setText(noteList.get(i).getNoteTitle());


                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        khoanThuDAO = new KhoanThuDAO(context);

                        //Em nhớ sửa id nhé  ...vì lúc đầu em khởi tạo id là rỗng nên id đâu
                        //mà updte
                        id = noteList.get(i).getNoteId();


                        Note note = new Note(id, edtTen.getText().toString());

                        if (khoanThuDAO.updateNote(note) == 1) {

                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_LONG).show();
                            viewHolder.tvName.setText(edtTen.getText().toString());

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
                khoanThuDAO = new KhoanThuDAO(context);
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
        TextView tvName;
        ImageView imgedit;
        ImageView imgdelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
            imgdelete = (ImageView) itemView.findViewById(R.id.imgdelete);


        }
    }
}
