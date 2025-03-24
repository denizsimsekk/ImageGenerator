package com.example.imagegenerator.downloadmanager

interface Downloader {
    fun downloadFile(url: String): Long
}