package com.sorago.homeinv.data.response.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class ListResponse<T> extends Response {
    private long total;
    private int offset;
    private int perPage;
    private List<T> data;

    public ListResponse(List<T> data) {
        this.data = data;
    }

    public ListResponse(List<T> data, long total, int offset, int perPage) {
        this.data = data;
        this.total = total;
        this.offset = offset;
        this.perPage = perPage;
    }

    public void add(T data) {
        this.data.add(data);
    }

    public void addAll(List<T> data) {
        this.data.addAll(data);
    }
}

