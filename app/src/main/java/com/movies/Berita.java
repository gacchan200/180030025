package com.movies;

import java.util.Date;

public class Berita {
    private int idBerita;
    private String jdlBerita;
    private Date tglBerita;
    private String gbrBerita;
    private String cptnBerita;
    private String authBerita;
    private String isiBerita;
    private String linkBerita;

    public Berita(int idBerita, String jdlBerita, Date tglBerita, String gbrBerita, String cptnBerita, String authBerita, String isiBerita, String linkBerita) {
        this.idBerita = idBerita;
        this.jdlBerita = jdlBerita;
        this.tglBerita = tglBerita;
        this.gbrBerita = gbrBerita;
        this.cptnBerita = cptnBerita;
        this.authBerita = authBerita;
        this.isiBerita = isiBerita;
        this.linkBerita = linkBerita;
    }

    public int getIdBerita() {
        return idBerita;
    }

    public String getJdlBerita() {
        return jdlBerita;
    }

    public Date getTglBerita() {
        return tglBerita;
    }

    public String getGbrBerita() {
        return gbrBerita;
    }

    public String getCptnBerita() {
        return cptnBerita;
    }

    public String getAuthBerita() {
        return authBerita;
    }

    public String getIsiBerita() {
        return isiBerita;
    }

    public String getLinkBerita() {
        return linkBerita;
    }
}

