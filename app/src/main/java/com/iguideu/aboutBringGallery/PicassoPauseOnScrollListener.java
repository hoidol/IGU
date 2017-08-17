package com.iguideu.aboutBringGallery;

import com.squareup.picasso.Picasso;

import cn.finalteam.galleryfinal.PauseOnScrollListener;

/**
 * Created by Hoyoung on 2017-08-13.
 */

public class PicassoPauseOnScrollListener extends PauseOnScrollListener {

    public PicassoPauseOnScrollListener(boolean pauseOnScroll, boolean pauseOnFling) {
        super(pauseOnScroll, pauseOnFling);
    }

    @Override
    public void resume() {
        Picasso.with(getActivity()).resumeTag(getActivity());
    }

    @Override
    public void pause() {
        Picasso.with(getActivity()).pauseTag(getActivity());
    }
}
