package com.kel3.yfaexpress.controller.report;

import com.kel3.yfaexpress.model.dto.TransaksiDto;
import com.kel3.yfaexpress.model.entity.Transaksi;
import com.kel3.yfaexpress.report.CustomJRDataSource;
import com.kel3.yfaexpress.repository.TransaksiRepository;
import net.sf.jasperreports.engine.*;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReportController {
    final static Logger logger = Logger.getLogger(ReportController.class);

    final static String pdfSource = "src/main/resources/static/jasper/report.pdf";

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private ModelMapper modelMapper;

    private void generateReport(List<TransaksiDto> transaksi) throws JRException{
        logger.info("[!] Start generate report");
        // Path to our template goes here
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/static/jasper/report.jrxml");
        // load data to datasource
        CustomJRDataSource<TransaksiDto> dataSource = new CustomJRDataSource<TransaksiDto>().using(transaksi);
        // Map datasource to template
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String,Object>(), dataSource);
        // Export to pdf
        JasperExportManager.exportReportToPdfFile(jasperPrint,pdfSource);
    }

    @GetMapping(value = "/report/{resi}")
    public HttpEntity<byte[]> getReport(@PathVariable String resi) throws JRException,IOException {
        // Stub data
        Transaksi transaksi = transaksiRepository.findByResiEquals(resi);
        TransaksiDto transaksiDto = new TransaksiDto();
        modelMapper.map(transaksi, transaksiDto);
        modelMapper.map(transaksi.getKurir(), transaksiDto);
        modelMapper.map(transaksi.getPengirim(), transaksiDto);
        modelMapper.map(transaksi.getPenerima(), transaksiDto);
        modelMapper.map(transaksi.getUseraa(), transaksiDto);
        List<TransaksiDto> tr = Arrays.asList(transaksiDto);
//        List<Transaksi> transaksi = transaksiRepository.findAll();
        generateReport(tr);
        logger.info("[+] Generated report successfully");

        // Force download
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.set("Content-Disposition",
                "attachment; filename=" + "FileNameHere.pdf");
        byte[] documentBody = Files.readAllBytes(new File(pdfSource).toPath());
        header.setContentLength(documentBody.length);
        return new HttpEntity<byte[]>(documentBody, header);
    }
}

