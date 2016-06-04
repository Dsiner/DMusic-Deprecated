package com.d.dmusic.commenadapter.recyclerview.support;

public interface SectionSupport<T> {
    public int sectionHeaderLayoutId();

    public int sectionTitleTextViewId();

    public String getTitle(T t);
}
