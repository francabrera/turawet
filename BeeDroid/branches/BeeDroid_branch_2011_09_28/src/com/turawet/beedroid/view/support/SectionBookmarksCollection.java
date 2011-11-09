package com.turawet.beedroid.view.support;

import java.util.ArrayList;
import java.util.List;

public class SectionBookmarksCollection
{
	
	public interface SectionBookmark
	{
		public void setSectionPage(int sectionPage);
		
		public int getSectionPage();
		
		public void setSectionName(String sectionName);
		
		public String getSectionName();
	}
	
	class SectionBookmarkImpl implements SectionBookmark
	{
		private String	sectionName;
		private int		sectionPage;
		
		public SectionBookmarkImpl(String name, int page)
		{
			this.sectionName = name;
			this.sectionPage = page;
		}
		
		public void setSectionPage(int sectionPage)
		{
			this.sectionPage = sectionPage;
		}
		
		public int getSectionPage()
		{
			return sectionPage;
		}
		
		public void setSectionName(String sectionName)
		{
			this.sectionName = sectionName;
		}
		
		public String getSectionName()
		{
			return sectionName;
		}
	}
	
	private List<SectionBookmark>	sectionBookmarkCollection;
	
	public SectionBookmarksCollection()
	{
		sectionBookmarkCollection = new ArrayList<SectionBookmark>();
	}
	
	/**
	 * @param name
	 * @param page
	 * @return
	 */
	public boolean addNewBookmark(String name, int page)
	{
		return sectionBookmarkCollection.add(new SectionBookmarkImpl(name, page));
	}
	
	/**
	 * @return
	 */
	public List<String> sectionsNames()
	{
		List<String> allSectionNames = new ArrayList<String>();
		for (SectionBookmark sectionBookmark : sectionBookmarkCollection)
			allSectionNames.add(sectionBookmark.getSectionName());
		return allSectionNames;
	}
	
	/**
	 * @param position
	 * @return
	 */
	public int getSectionPageAt(int position)
	{
		return sectionBookmarkCollection.get(position).getSectionPage();
	}
}
