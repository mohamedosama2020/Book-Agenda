package com.example.moham.bookagenda;

import java.util.ArrayList;

class BookItem {

    private VolumeInfo volumeInfo;

    public BookItem(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;

    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
