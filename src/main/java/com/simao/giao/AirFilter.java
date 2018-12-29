package com.simao.giao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AirFilter extends Filter{
    static class Factory implements com.simao.giao.Factory<AirFilter> {

        @Override
        public AirFilter create() {
            List arrayList = new ArrayList();
            List list = Collections.synchronizedList(arrayList);
            return new AirFilter();
        }
    }
}
