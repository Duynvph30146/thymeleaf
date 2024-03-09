package com.example.demo.controller;

import com.example.demo.dto.Info;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.KhachHang;
import com.example.demo.repository.HoaDonRepo;
import com.example.demo.repository.KhachHangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HoaDonController {
    @Autowired
    KhachHangRepo khachHangRepo;
    @Autowired
    HoaDonRepo hoaDonRepo;

    @GetMapping("/list")
    public String hienthi(@ModelAttribute("tim") Info info, Model model, @RequestParam(defaultValue = "0") int p) {
        Pageable pageable = PageRequest.of(p, 5);
        Page<HoaDon> page = null;
        if (info.getKey() != null) {
            page = hoaDonRepo.findAllByNgaylapGreaterThanEqualAndNgaythanhtoanLessThanEqualOrNguoilap
                    (info.getNgaybd(), info.getNgaykt(), info.getKey(), pageable);
        } else {
            page = hoaDonRepo.findAll(pageable);
        }
        model.addAttribute("page", page);
        return "index";
    }

    @GetMapping("/add")
    public String viewAdd(@ModelAttribute("hd") HoaDon hoaDon) {
        return "add";
    }

    @PostMapping("/add")
    public String addSave(@ModelAttribute("hd") HoaDon hoaDon) {
        hoaDonRepo.save(hoaDon);
        return "redirect:/list";
    }

    @GetMapping("/delete/{mahoadon}")
    public String delete(@PathVariable Integer mahoadon) {
        hoaDonRepo.deleteById(mahoadon);
        return "redirect:/list";
    }

    @GetMapping("/edit/{mahoadon}")
    public String edit(@PathVariable Integer mahoadon, Model model) {
        HoaDon hoaDon = hoaDonRepo.findById(mahoadon).orElse(null);
        model.addAttribute("hd", hoaDon);
        return "edit";
    }

    @PostMapping("/edit/{mahoadon}")
    public String update(@PathVariable Integer mahoadon, @ModelAttribute("hd") HoaDon hoaDon) {
        hoaDon.setMahoadon(mahoadon);
        hoaDonRepo.save(hoaDon);
        return "redirect:/list";
    }

    @ModelAttribute("dsKH")
    public List<KhachHang> getDS() {
        return khachHangRepo.findAll();
    }
}
