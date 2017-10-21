package com.yo.cx.cxyo;

/**
 * Created by kelvin on 10/21/17.
 */

class FrequentRequest {
    private String request;
    private int image;

    public FrequentRequest(String request, int image) {
        this.request = request;
        this.image = image;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
