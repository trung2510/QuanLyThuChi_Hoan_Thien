package com.example.quanlythuchi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quanlythuchi.dao.KhoanChiDAO;
import com.example.quanlythuchi.dao.KhoanThuDAO;
import com.example.quanlythuchi.dao.LoaiChiDAO;
import com.example.quanlythuchi.dao.LoaiThuDAO;
import com.example.quanlythuchi.model.Note;

import java.util.List;

public class ThongKeFragment extends Fragment {

    private TextView tvthu;
    private TextView tvchi;
    private TextView tvconlai;
    KhoanThuDAO khoanThuDAO ;
    KhoanChiDAO khoanChiDAO;
    LoaiThuDAO loaiThuDAO;
    LoaiChiDAO loaiChiDAO;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thongke,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvthu = (TextView) view.findViewById(R.id.tvthu);
        tvchi = (TextView) view.findViewById(R.id.tvchi);
        tvconlai = (TextView) view.findViewById(R.id.tvconlai);

        khoanThuDAO = new KhoanThuDAO(getContext());
        khoanChiDAO= new KhoanChiDAO(getContext());
        loaiThuDAO= new LoaiThuDAO(getContext());
        loaiChiDAO= new LoaiChiDAO(getContext());

//        Double notes = khoanThuDAO.getThu();
//        Double note2 = khoanChiDAO.getThu();
        Double note3 = loaiThuDAO.getThu();
        Double note4 = loaiChiDAO.getThu();

        tvthu.setText("Tổng thu: " + (note3) + " VNĐ");
        tvchi.setText("Tổng chi: " + (note4) + " VNĐ");
        tvconlai.setText("Còn lại: " + (note3-note4) + " VNĐ");

    }
}
