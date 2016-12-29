package cn.edu.bjtu.weibo.service;

import java.util.ArrayList;
import java.util.List;

import cn.edu.bjtu.weibo.model.BaseContent;
import cn.edu.bjtu.weibo.model.Comment;
import cn.edu.bjtu.weibo.model.Weibo;


public  class AtMeServiceImpl implements AtMeService{

	
	WeiboDao wdao = new WeiboDao();
	CommentDao cdao = new CommentDao();
	List<BaseContent> list = new ArrayList<BaseContent>();
	@Override
	public List<BaseContent> getAtMeContentList(String userId, int pageIndex, int numberPerPage) {
		// TODO Auto-generated method stub
		String searchContent = "@" + userId;
		List<Weibo> weiboList = wdao.queryList("");
		List<Comment> commentList = cdao.queryCommentList("");
		//遍历微博列表查找@用户
		for(int i = 0;i < weiboList.size();i++){
			if(isContain(weiboList.get(i).getContent(),searchContent)==true){
				list.add(weiboList.get(i));
			}
		}
		
		
		//遍历评论列表查找@用户
		String commentId = null;
		for(int i = 0;i < commentList.size();i++){
			if(isContain(commentList.get(i).getComment(commentId),searchContent)==true){
				
				
				list.add(commentList.get(i));
			}
		}
		//按照时间将List中的BaseContent排序
		BaseContent temp ;
		for(int i = 0; i< list.size(); i++){
			for(int j = i+1;j<list.size();j++){
			String date1 = list.get(i).getDate();	
			String date2 = list.get(j).getDate();

			int d1 = Integer.parseInt(date1);
			int d2 = Integer.parseInt(date2);
			if(d2>d1){
				//交换
				temp = list.get(i);
				list.remove(i);
				list.add(j, temp);
				
			}
		}
	}
		return list;
  }
	
	
	//判断内容中是否包含需查询的key
	public static boolean isContain(String s1,String s2) {

		return s1.contains(s2);

		} 

	
	//排序
	

}
