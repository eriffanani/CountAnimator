package com.erif.countanimator;

public class Abc {

    private void a() {
        /*
        val anim = CountAnimator(0, 1200)
        anim.doOnUpdate { _, valueStr ->
            txt.text = valueStr
        }
        anim.start()
        */

        CountAnimator anim = new CountAnimator(1200, 50);
        anim.duration(200L);
        anim.doOnUpdate((value, valueStr) -> {

        });
        anim.start();
    }

}
