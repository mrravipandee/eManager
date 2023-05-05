package com.mrravipande.emanager.eBook;

public class eBookData {

    private String pdfTitle, pdfUrl;

    public eBookData() {

    }

    public eBookData(String pdfTitle, String pdfUrl) {
        this.pdfTitle = pdfTitle;
        this.pdfUrl = pdfUrl;
    }

    public String getPdfTitle() {
        return pdfTitle;
    }

    public void setPdfTitle(String pdfTitle) {
        this.pdfTitle = pdfTitle;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
