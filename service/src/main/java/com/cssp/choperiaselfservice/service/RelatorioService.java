package com.cssp.choperiaselfservice.service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class RelatorioService {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "admin";
        String password = "admin";
        return DriverManager.getConnection(url, username, password);
    }

    public byte[] balanceReportProductInStock() throws JRException, SQLException {
        Connection connection = getConnection();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/relatorio-estoque-produtos.jrxml")
        );

        Map<String, Object> params = new HashMap<>();
        params.put(JRParameter.REPORT_CONNECTION, connection);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
        connection.close();
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] pointOfOrderProductReport() throws JRException, SQLException {
        Connection connection = getConnection();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/relatorio-ponto-encomenda-produtos.jrxml")
        );

        Map<String, Object> params = new HashMap<>();
        params.put(JRParameter.REPORT_CONNECTION, connection);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
        connection.close();
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] reportMostConsumedBeersInAPeriod(Date dataInicial, Date dataFinal) throws JRException, SQLException {
        Connection connection = getConnection();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/relatorio-chopes-populares.jrxml")
        );

        Map<String, Object> params = new HashMap<>();
        params.put(JRParameter.REPORT_CONNECTION, connection);
        params.put("DataInicial", dataInicial);
        params.put("DataFinal", dataFinal);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
        connection.close();
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] customerReportWithAmountPurchasedInPeriod(Date dataInicial, Date dataFinal) throws JRException, SQLException {
        Connection connection = getConnection();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/relatorio-total-compras-clientes.jrxml")
        );

        Map<String, Object> params = new HashMap<>();
        params.put(JRParameter.REPORT_CONNECTION, connection);
        params.put("DataInicial", dataInicial);
        params.put("DataFinal", dataFinal);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
        connection.close();
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] expenseAndIncomeMovementReport(Date dataInicial, Date dataFinal) throws JRException, SQLException {
        Connection connection = getConnection();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/relatorio-receita-despesas.jrxml")
        );

        Map<String, Object> params = new HashMap<>();
        params.put(JRParameter.REPORT_CONNECTION, connection);
        params.put("DataInicial", dataInicial);
        params.put("DataFinal", dataFinal);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
        connection.close();
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
