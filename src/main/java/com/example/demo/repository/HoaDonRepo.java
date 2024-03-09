package com.example.demo.repository;

import com.example.demo.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface HoaDonRepo extends JpaRepository<HoaDon, Integer> {

    Page<HoaDon> findAllByNgaylapGreaterThanEqualAndNgaythanhtoanLessThanEqualOrNguoilap
            (Date ngaybd, Date ngaykt, String nguoilap, Pageable pageable);
}
