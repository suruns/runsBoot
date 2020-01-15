package com.suruns.runsSpringBoot.common;

import org.apache.logging.log4j.util.Strings;

import java.util.*;

/**
 * @Author: suruns
 * @Date: 2020年01月13日 14:30
 * @Desc: 正常返回值
 */
public class RunsResponse implements Map<String, Object>, ErrorResponse {

    private static final int DEFAULT_CAPACITY = 3;
    private static final String DEFAULT_CODE_KEY = "code";
    private static final String DEFAULT_MESSAGE_KEY = "message";
    private static final String DEFAULT_DATA_KEY = "data";

    private int size = 0;
    private int capacity;
    private String[] keys;
    private Object[] values;

    public RunsResponse() {
        this(DEFAULT_CAPACITY);
    }

    public RunsResponse(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("map size must > 0");
        }
        this.capacity = capacity;
        this.keys = new String[capacity];
        this.values = new Object[capacity];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean containsKey(Object key) {
       return indexOfKey(key) > 0;
    }

    private int indexOfKey(Object key) {
        if (isEmpty()) {
            return -1;
        }
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(key, keys[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get(Object key) {
        int index = indexOfKey(key);
        if (index > 0){
            return values[index];
        } else {
            return null;
        }
    }

    @Override
    public Object put(String key, Object value) {
        if (Strings.isBlank(key)) {
            throw new IllegalArgumentException();
        }
        checkSize(1);
        int index = this.size++;
        this.keys[index] = key;
        this.values[index] = value;
        return value;
    }

    private void checkSize(int growSize) {
        boolean changeCapacity = false;
        if (this.size == this.capacity) {
            this.capacity = this.capacity << 2;
            changeCapacity = true;
        }

        int minSize = this.size + growSize;

        if (this.capacity < minSize) {
            this.capacity = minSize;
            changeCapacity = true;
        }
        if (changeCapacity) {
            String[] oldKeys = this.keys;
            Object[] oldValues = this.values;

            this.keys = new String[this.capacity];
            this.values = new Object[this.capacity];

            System.arraycopy(oldKeys, 0, this.keys, 0,
                    this.size);
            System.arraycopy(oldValues, 0, this.values, 0,
                    this.size);
        }
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        if (Objects.isNull(m)) {
            throw new IllegalArgumentException();
        }
        checkSize(m.size());
        for (Entry entry : m.entrySet()) {
            int index = this.size++;
            this.keys[index] = entry.getKey().toString();
            this.values[index] = entry.getValue();
        }
    }

    public void setCode(Object code) {
        this.put(DEFAULT_CODE_KEY, code);
    }

    public void setData(Object data) {
        this.put(DEFAULT_DATA_KEY, data);
    }

    public void setMessage(Object message) {
        this.put(DEFAULT_MESSAGE_KEY, message);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Object> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        if (isEmpty()) {
            return Collections.EMPTY_SET;
        } else {
            Set<Entry<String, Object>> entries = new HashSet<>();
            for (int i = 0; i < this.size; i++) {
                entries.add(new RunsEntry(keys[i], values[i]));
            }
            return entries;
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Object getCode() {
        return get(DEFAULT_CODE_KEY);
    }

    @Override
    public Object getMessage() {
        return get(DEFAULT_MESSAGE_KEY);
    }

    public static class Builder {
        private RunsResponse runsResponse;

        private void checkInstance() {
            if (Objects.isNull(runsResponse)) {
                this.runsResponse = new RunsResponse();
            }
        }

        public Builder instance(int size) {
            this.runsResponse = new RunsResponse(size);
            return this;
        }

        public Builder instance(Object code, Object message) {
            this.runsResponse = new RunsResponse();
            this.runsResponse.setCode(code);
            this.runsResponse.setMessage(message);
            return this;
        }

        public Builder instance(ErrorResponse response) {
            this.runsResponse = new RunsResponse();
            runsResponse.setCode(response.getCode());
            runsResponse.setMessage(response.getMessage());
            return this;
        }

        public Builder instance(Object code, Object message, Object data) {
            this.runsResponse = new RunsResponse();
            this.runsResponse.setCode(code);
            this.runsResponse.setMessage(message);
            this.runsResponse.setData(data);
            return this;
        }

        public Builder setData(Object data) {
            checkInstance();
            this.runsResponse.setData(data);
            return this;
        }

        public Builder setCode(Object code) {
            checkInstance();
            this.runsResponse.setCode(code);
            return this;
        }

        public Builder setMessage(Object message) {
            checkInstance();
            this.runsResponse.setMessage(message);
            return this;
        }

        public Builder put(String key, Object value) {
            checkInstance();
            this.runsResponse.put(key, value);
            return this;
        }

        public Builder putAll(Map<String, Object> map) {
            checkInstance();
            this.runsResponse.putAll(map);
            return this;
        }

        public RunsResponse build() {
            checkInstance();
            return this.runsResponse;
        }
    }

    private class RunsEntry implements Entry<String, Object> {

        private String key;
        private Object value;

        public RunsEntry(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public Object getValue() {
            return this.value;
        }

        @Override
        public Object setValue(Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof RunsEntry)) {
                return false;
            }
            RunsEntry runsEntry = (RunsEntry) o;
            return Objects.equals(getKey(), runsEntry.getKey()) &&
                    Objects.equals(getValue(), runsEntry.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getKey(), getValue());
        }
    }
}
