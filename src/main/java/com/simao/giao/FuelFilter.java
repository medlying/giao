package com.simao.giao;

public class FuelFilter extends Filter {
    static class Factory implements com.simao.giao.Factory<FuelFilter> {

        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}
