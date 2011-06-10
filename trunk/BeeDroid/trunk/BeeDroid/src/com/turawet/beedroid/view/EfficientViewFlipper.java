package com.turawet.beedroid.view;

import com.turawet.beedroid.beans.InstanceBean;
import com.turawet.beedroid.view.support.BeanToViewGenerator;

import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Efficient management of a dynamic flipper list of view
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */

public class EfficientViewFlipper extends FrameLayout
{
	private int							width;
	private int							height;
	private LinearLayout				previousView;
	private LinearLayout				nextView;
	private LinearLayout				currentView;
	private BeanToViewGenerator	viewsGenerator;
	
	/**
	 * @param context
	 *           Context where the class its beign created
	 * @param instance
	 *           Instance of the form where the views are gonna be taken
	 */
	public EfficientViewFlipper(Context context, InstanceBean instance)
	{
		super(context);
		
		/*
		 * Create a bean to view translator
		 */
		viewsGenerator = new BeanToViewGenerator(context, instance);
		
		/*
		 * We get first two views dynamically through the view manager
		 * current and next ones
		 */
		currentView = (LinearLayout) viewsGenerator.getCurrentView();
		nextView = (LinearLayout) viewsGenerator.getNextView();
		
		/*
		 * If the forms have just one or two views, we can get a null view
		 * from the manager
		 */
		if (nextView != null)
			addView(nextView);
		if (currentView != null)
		{
			addView(currentView);
			bringChildToFront(currentView);
		}
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	/**
	 * Shows the current view when tries to move beyon the last or first
	 */
	public void backToCurrent()
	{
		/*
		 * Moves the previous one to the left
		 */
		if (previousView != null)
			previousView.layout(-width, previousView.getTop(), 0, previousView.getBottom());
		/*
		 * Current one to the front
		 */
		currentView.layout(0, currentView.getTop(), width, currentView.getBottom());
		/*
		 * ...and next one to the right
		 */
		if (nextView != null)
			nextView.layout(width, nextView.getTop(), 2 * width, nextView.getBottom());
		
	}
	
	/**
	 * Shows the previous view
	 */
	public void showPrevious()
	{
		if (viewsGenerator.canDecreaseIndex())
		{
			
			removeAllViews();
			
			boolean updated = viewsGenerator.updateBeforeDecrease();
			nextView = currentView;
			currentView = previousView;
			previousView = (LinearLayout) viewsGenerator.getPrevView();
			if (previousView != null)
				addView(previousView);
			addView(currentView);
			if (nextView != null)
				addView(nextView);
			
			bringChildToFront(currentView);
			if (!updated)
				viewsGenerator.decIndex();
		}
		else
		{
			currentView.layout(0, currentView.getTop(), width, currentView.getBottom());
			nextView.layout(width, nextView.getTop(), 2 * width, nextView.getBottom());
		}
	}
	
	/**
	 * Shows the next view
	 */
	public void showNext()
	{
		if (viewsGenerator.canIncreaseIndex())
		{
			removeAllViews();
			
			boolean updated = viewsGenerator.updateBeforeIncrease();
			
			previousView = currentView;
			currentView = nextView;
			nextView = (LinearLayout) viewsGenerator.getNextView();
			if (previousView != null)
				addView(previousView);
			addView(currentView);
			if (nextView != null)
				addView(nextView);
			
			bringChildToFront(currentView);
			
			if (!updated)
				viewsGenerator.incIndex();
		}
		else
		{
			previousView.layout(-width, previousView.getTop(), 0, previousView.getBottom());
			currentView.layout(0, currentView.getTop(), width, currentView.getBottom());
		}
	}
	
	/**
	 * Moves the three views together along with the finger
	 * 
	 * @param oldX
	 *           Original position
	 * @param currentX
	 *           New position
	 */
	public void move(int oldX, int currentX)
	{
		if (previousView != null)
			previousView.layout(currentX - oldX - width, previousView.getTop(), currentX - oldX, previousView.getBottom());
		currentView.layout(currentX - oldX, currentView.getTop(), currentX - oldX + width, currentView.getBottom());
		if (nextView != null)
			nextView.layout(currentX - oldX + width, nextView.getTop(), currentX - oldX + 2 * width, nextView.getBottom());
	}
	
	/**
	 * @param height
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}
	
}
