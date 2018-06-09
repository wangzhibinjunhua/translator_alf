package com.jachat.translateor.fragment;

import com.jachat.translateor.R;
import com.jachat.translateor.base.BaseFragment;
import com.jachat.translateor.base.BasePresenter;

public class FragmentTranslationConversation extends BaseFragment{


	@Override
	protected int getLayoutId() {
		return R.layout.fragment_translation_conversation;
	}

	@Override
	protected BasePresenter onCreatePresenter() {
		return null;
	}

	@Override
	protected boolean setEventBus() {
		return false;
	}

	@Override
	protected void initData() {

	}
}
