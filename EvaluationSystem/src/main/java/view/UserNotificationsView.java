package view;

import model.GroupInvitation;
import model.Notification;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserNotificationsView extends UserView {

    private List<NotificationView> notifications;

    public UserNotificationsView(){}

    public UserNotificationsView(User user){
        super(user);
        this.notifications = new ArrayList<>();
        for(Notification notification: user._notifications.toArray()) {
            switch (notification.getClass().getCanonicalName()){
                case "GroupInvitation":
                    this.notifications.add(new GroupInvitationView((GroupInvitation)notification));
                    break;
            }
        }
    }

    public List<NotificationView> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationView> notifications) {
        this.notifications = notifications;
    }
}
