
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	 <div class="container">
	   <h1>Your ToDos are:</h1>
	   <table class="table">
	      <thead>
		   <tr>
		      <th>Description</th>
		      <th>Target date</th>
		      <th>Is Done?</th>
		      <th></th>
		      <th></th>
		    </tr>
		   </thead>
	   		<tbody>
			   <c:forEach items="${todos}" var="todo">
			     <tr>
			       <td>${todo.description}</td>
			       <td>${todo.targetDate}</td>
			       <td>${todo.done}</td>
			       <td><a href="delete-todo?id=${todo.id}" class="btn btn-danger">DELETE</a></td>
			       <td><a href="update-todo?id=${todo.id}" class="btn btn-primary">UPDATE</a></td>
	             </tr>
			   </c:forEach>
		   </tbody>
	   </table>
	   <a href="todo" class="btn btn-success">Add ToDo</a>
	 </div>
  
<%@ include file="common/footer.jspf" %>