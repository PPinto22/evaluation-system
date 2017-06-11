package wrapper;

import model.GroupInvitation;
import model.Notification;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserNotificationsWrapper extends UserWrapper {

    private List<NotificationWrapper> notifications;

    public UserNotificationsWrapper(){}

    public UserNotificationsWrapper(User user){
        super(user);
        this.notifications = new ArrayList<>();
        for(Notification notification: user._notifications.toArray()) {
            switch (notification.getClass().getCanonicalName()){
                case "GroupInvitation":
                    this.notifications.add(new GroupInvitationWrapper((GroupInvitation)notification));
                    break;
            }
        }
    }

    public List<NotificationWrapper> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationWrapper> notifications) {
        this.notifications = notifications;
    }
}
