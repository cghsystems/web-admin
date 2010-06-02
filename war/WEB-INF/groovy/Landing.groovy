import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();

def index = "index.html"
if (user == null) {
	index = userService.createLoginURL(index)
} 

response.sendRedirect(index);