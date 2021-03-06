/**
 * 
 */
package com.turawet.beedroid.view;

/*
 * Copyright (C) 2011 Jason Fry
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Jason Fry - jasonfry.co.uk
 * @version 1.1.8
 * 
 */

//import com.turawet.beedroid.view.PageControl.OnPageControlClickListener;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class BeanViewFlipper extends HorizontalScrollView
{
	private static int				DEFAULT_SWIPE_THRESHOLD			= 60;
	
	private LinearLayout				mLinearLayout;
	private Context					mContext;
	private int							SCREEN_WIDTH;
	private int							mMotionStartX;
	private int							mMotionStartY;
	private boolean					mMostlyScrollingInX				= false;
	private boolean					mMostlyScrollingInY				= false;
	private boolean					mJustInterceptedAndIgnored		= false;
	protected boolean					mCallScrollToPageInOnLayout	= false;
	private int							mCurrentPage						= 0;
	private int							mPageWidth							= 0;
	private OnPageChangedListener	mOnPageChangedListener			= null;
	private SwipeOnTouchListener	mSwipeOnTouchListener;
	private View.OnTouchListener	mOnTouchListener;
//	private PageControl				mPageControl						= null;
	
	/**
	 * {@inheritDoc}
	 */
	public BeanViewFlipper(Context context)
	{
		super(context);
		mContext = context;
		initSwipeView();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public BeanViewFlipper(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mContext = context;
		initSwipeView();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public BeanViewFlipper(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		mContext = context;
		initSwipeView();
	}
	
	private void initSwipeView()
	{
		mLinearLayout = new LinearLayout(mContext);
		mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		super.addView(mLinearLayout, -1, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
		setSmoothScrollingEnabled(true);
		setHorizontalFadingEdgeEnabled(false);
		setHorizontalScrollBarEnabled(false);
		
		Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		SCREEN_WIDTH = (int) (display.getWidth());
		mPageWidth = SCREEN_WIDTH;
		mCurrentPage = 0;
		
		mSwipeOnTouchListener = new SwipeOnTouchListener();
		super.setOnTouchListener(mSwipeOnTouchListener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onTrackballEvent(MotionEvent event)
	{
		return true;
	}
	
	@Override
	protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect)
	{
		// this will now pass trackball events down to onTrackballEvent
		return false;
	}
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addView(View child)
	{
		this.addView(child, -1);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addView(View child, int index)
	{
		ViewGroup.LayoutParams params;
		if (child.getLayoutParams() == null)
		{
			params = new LayoutParams(mPageWidth, LayoutParams.FILL_PARENT);
		}
		else
		{
			params = child.getLayoutParams();
			params.width = mPageWidth;
		}
		this.addView(child, index, params);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addView(View child, ViewGroup.LayoutParams params)
	{
		params.width = mPageWidth;
		this.addView(child, -1, params);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addView(View child, int index, ViewGroup.LayoutParams params)
	{
		requestLayout();
		invalidate();
		mLinearLayout.addView(child, index, params);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		super.onLayout(changed, l, t, r, b);
		if (mCallScrollToPageInOnLayout)
		{
			scrollToPage(mCurrentPage);
			mCallScrollToPageInOnLayout = false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setOnTouchListener(View.OnTouchListener onTouchListener)
	{
		mOnTouchListener = onTouchListener;
	}
	
	/**
	 * Get the View object that contains all the children of this SwipeView. The
	 * same as calling getChildAt(0)
	 * A SwipeView behaves slightly differently from a normal ViewGroup, all the
	 * children of a SwipeView
	 * sit within a LinearLayout, which then sits within the SwipeView object.
	 * 
	 * @return linearLayout The View object that contains all the children of
	 *         this view
	 */
	public LinearLayout getChildContainer()
	{
		return mLinearLayout;
	}
	
	/**
	 * Get the swiping threshold distance to make the screens change
	 * 
	 * @return swipeThreshold The minimum distance the finger should move to
	 *         allow the screens to change
	 */
	public int getSwipeThreshold()
	{
		return DEFAULT_SWIPE_THRESHOLD;
	}
	
	/**
	 * Set the swiping threshold distance to make the screens change
	 * 
	 * @param swipeThreshold
	 *           The minimum distance the finger should move to allow the screens
	 *           to change
	 */
	public void setSwipeThreshold(int swipeThreshold)
	{
		DEFAULT_SWIPE_THRESHOLD = swipeThreshold;
	}
	
	/**
	 * Get the current page the SwipeView is on
	 * 
	 * @return The current page the SwipeView is on
	 */
	public int getCurrentPage()
	{
		return mCurrentPage;
	}
	
	/**
	 * Return the number of pages in this SwipeView
	 * 
	 * @return Returns the number of pages in this SwipeView
	 */
	public int getPageCount()
	{
		return mLinearLayout.getChildCount();
	}
	
	/**
	 * Go directly to the specified page
	 * 
	 * @param page
	 *           The page to scroll to
	 */
	public void scrollToPage(int page)
	{
		scrollToPage(page, false);
	}
	
	/**
	 * Animate a scroll to the specified page
	 * 
	 * @param page
	 *           The page to animate to
	 */
	public void smoothScrollToPage(int page)
	{
		scrollToPage(page, true);
	}
	
	private void scrollToPage(int page, boolean smooth)
	{
		int oldPage = mCurrentPage;

		if (page >= getPageCount() && getPageCount() > 0)
		{
			page--;
		}
		else if (page < 0)
		{
			page = 0;
		}
		
		if (smooth)
		{
			smoothScrollTo(page * mPageWidth, 0);
		}
		else
		{
			scrollTo(page * mPageWidth, 0);
		}
		mCurrentPage = page;
		
		if (mOnPageChangedListener != null && oldPage != page)
		{
			mOnPageChangedListener.onPageChanged(oldPage, page);
		}

		mCallScrollToPageInOnLayout = !mCallScrollToPageInOnLayout;
	}
	
	/**
	 * Set the width of each page. This function returns an integer that should
	 * be added to the left margin of
	 * the first child and the right margin of the last child. This enables all
	 * the children to appear to be
	 * central
	 * 
	 * @param pageWidth
	 *           The width you wish to assign for each page
	 * @return An integer to add to the left margin of the first child and the
	 *         right margin of the last child
	 */
	public int setPageWidth(int pageWidth)
	{
		mPageWidth = pageWidth;
		return (SCREEN_WIDTH - mPageWidth) / 2;
	}
	
	/**
	 * Set the width of each page by using the layout parameters of a child. Call
	 * this function before you add
	 * the child to the SwipeView to maintain the child's size. This function
	 * returns an integer that should
	 * be added to the left margin of the first child and the right margin of the
	 * last child. This enables all
	 * the children to appear to be central
	 * 
	 * @param childLayoutParams
	 *           A child view that you have added / will add to the SwipeView
	 * @return An integer to add to the left margin of the first child and the
	 *         right margin of the last child
	 */
	public int calculatePageSize(MarginLayoutParams childLayoutParams)
	{
		return setPageWidth(childLayoutParams.leftMargin + childLayoutParams.width + childLayoutParams.rightMargin);
	}
	
	/**
	 * Return the current width of each page
	 * 
	 * @return Returns the width of each page
	 */
	public int getPageWidth()
	{
		return mPageWidth;
	}
		
	/**
	 * Implement this listener to listen for page change events
	 * 
	 * @author Jason Fry - jasonfry.co.uk
	 * 
	 */
	public interface OnPageChangedListener
	{
		/**
		 * Event for when a page changes
		 * 
		 * @param oldPage
		 *           The page the view was on previously
		 * @param newPage
		 *           The page the view has moved to
		 */
		public abstract void onPageChanged(int oldPage, int newPage);
	}
	
	/**
	 * Set the current OnPageChangedListsner
	 * 
	 * @param onPageChangedListener
	 *           The OnPageChangedListener object
	 */
	public void setOnPageChangedListener(OnPageChangedListener onPageChangedListener)
	{
		mOnPageChangedListener = onPageChangedListener;
	}
	
	/**
	 * Get the current OnPageChangeListsner
	 * 
	 * @return The current OnPageChangedListener
	 */
	public OnPageChangedListener getOnPageChangedListener()
	{
		return mOnPageChangedListener;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		boolean result = super.onInterceptTouchEvent(ev);
		
		if (ev.getAction() == MotionEvent.ACTION_DOWN)
		{
			mMotionStartX = (int) ev.getX();
			mMotionStartY = (int) ev.getY();
			if (!mJustInterceptedAndIgnored)
			{
				mMostlyScrollingInX = false;
				mMostlyScrollingInY = false;
			}
		}
		else if (ev.getAction() == MotionEvent.ACTION_MOVE)
		{
			detectMostlyScrollingDirection(ev);
		}
		
		if (mMostlyScrollingInY)
		{
			return false;
		}
		if (mMostlyScrollingInX)
		{
			mJustInterceptedAndIgnored = true;
			return true;
		}
		
		return result;
	}
	
	private void detectMostlyScrollingDirection(MotionEvent ev)
	{
		/* if we dont know which direction we're going yet */
		if (!mMostlyScrollingInX && !mMostlyScrollingInY)
		{
			float xDistance = Math.abs(mMotionStartX - ev.getX());
			float yDistance = Math.abs(mMotionStartY - ev.getY());
			
			if (yDistance > xDistance + 5)
			{
				mMostlyScrollingInY = true;
			}
			else if (xDistance > yDistance + 5)
			{
				mMostlyScrollingInX = true;
			}
		}
	}
	
	public void adjustPageWidth()
	{
		Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		SCREEN_WIDTH = mPageWidth = (int) (display.getWidth());
		
		ViewGroup.LayoutParams params = mLinearLayout.getLayoutParams();
		params.width = mPageWidth;
		mLinearLayout.setLayoutParams(params);
		
		int numOfChilds = mLinearLayout.getChildCount();
		
		for (int child = 0; child < numOfChilds; child++)
		{
			View viewChild = mLinearLayout.getChildAt(child);
			params = viewChild.getLayoutParams();
			params.width = mPageWidth;
			viewChild.setLayoutParams(params);
		}
		smoothScrollToPage(mCurrentPage);
	}
	
	private class SwipeOnTouchListener implements View.OnTouchListener
	{
		private boolean	mSendingDummyMotionEvent	= false;
		private int			mDistanceX;
		private int			mPreviousDirection;
		private boolean	mFirstMotionEvent				= true;
		
		public boolean onTouch(View v, MotionEvent event)
		{
			if (mOnTouchListener != null && !mJustInterceptedAndIgnored || mOnTouchListener != null && mSendingDummyMotionEvent)
			{
				if (mOnTouchListener.onTouch(v, event))
				{
					if (event.getAction() == MotionEvent.ACTION_UP)
					{
						/*
						 * need to call the actionUp directly so the view is not left
						 * between pages.
						 */
						actionUp(event);
					}
					return true;
				}
			}
			
			if (mSendingDummyMotionEvent)
			{
				mSendingDummyMotionEvent = false;
				return false;
			}
			
			switch (event.getAction())
			{
				case MotionEvent.ACTION_DOWN:
					return actionDown(event);
					
				case MotionEvent.ACTION_MOVE:
					return actionMove(event);
					
				case MotionEvent.ACTION_UP:
					return actionUp(event);
			}
			return false;
		}
		
		private boolean actionDown(MotionEvent event)
		{
			mMotionStartX = (int) event.getX();
			mMotionStartY = (int) event.getY();
			mFirstMotionEvent = false;
			return false;
		}
		
		private boolean actionMove(MotionEvent event)
		{
			int newDistance = mMotionStartX - (int) event.getX();
			int newDirection;
			
			if (newDistance < 0) // backwards
			{
				newDirection = (mDistanceX + 4 <= newDistance) ? 1 : -1;
			}
			else
			// forwards
			{
				newDirection = (mDistanceX - 4 <= newDistance) ? 1 : -1;
			}
			
			if (newDirection != mPreviousDirection && !mFirstMotionEvent)
			{
				mMotionStartX = (int) event.getX();
				mDistanceX = mMotionStartX - (int) event.getX();
			}
			else
			{
				mDistanceX = newDistance;
			}
			
			mPreviousDirection = newDirection; // backwards -1, forwards is 1,
			
			if (mJustInterceptedAndIgnored)
			{
				mSendingDummyMotionEvent = true;
				dispatchTouchEvent(MotionEvent.obtain(event.getDownTime(), event.getEventTime(), MotionEvent.ACTION_DOWN, mMotionStartX, mMotionStartY, event.getPressure(), event.getSize(), event.getMetaState(), event.getXPrecision(), event.getYPrecision(), event.getDeviceId(), event.getEdgeFlags()));
				mJustInterceptedAndIgnored = false;
				
				return true;
			}
			return false;
		}
		
		private boolean actionUp(MotionEvent event)
		{
			float fingerUpPosition = getScrollX();
			float numberOfPages = mLinearLayout.getMeasuredWidth() / mPageWidth;
			float fingerUpPage = fingerUpPosition / mPageWidth;
			float edgePosition = 0;
			
			if (mPreviousDirection == 1) // forwards
			{
				if (mDistanceX > DEFAULT_SWIPE_THRESHOLD)// if over then go forwards
				{
					if (mCurrentPage < (numberOfPages - 1))
					{
						edgePosition = (int) (fingerUpPage + 1) * mPageWidth;
					}
					else
					{
						edgePosition = (int) (mCurrentPage) * mPageWidth;
					}
				}
				else
				// return to start position
				{
					if (Math.round(fingerUpPage) == numberOfPages - 1)
					{
						edgePosition = (int) (fingerUpPage + 1) * mPageWidth;
					}
					else
					// carry on as normal
					{
						edgePosition = mCurrentPage * mPageWidth;
					}
				}
				
			}
			else
			// backwards
			{
				if (mDistanceX < -DEFAULT_SWIPE_THRESHOLD)// go backwards
				{
					edgePosition = (int) (fingerUpPage) * mPageWidth;
				}
				else
				// return to start position
				{
					if (Math.round(fingerUpPage) == 0)// if at beginning, correct
					{
						/*
						 * need to correct for when user starts to scroll into
						 * nothing then pulls it back a bit, this becomes a
						 * kind of backwards scroll instead
						 */
						edgePosition = (int) (fingerUpPage) * mPageWidth;
					}
					else
					// carry on as normal
					{
						edgePosition = mCurrentPage * mPageWidth;
					}
					
				}
			}
			
			smoothScrollToPage((int) edgePosition / mPageWidth);
			mFirstMotionEvent = true;
			mDistanceX = 0;
			mMostlyScrollingInX = false;
			mMostlyScrollingInY = false;
			
			return true;
		}
	}
}
