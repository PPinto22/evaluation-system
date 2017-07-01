import { Component, OnInit } from '@angular/core';
import {Notification} from '../../models/notification';
import {AuthenticationService} from '../../services/authentication.service';
import {NotificationService} from '../../services/notification.service';
import {Group} from '../../models/group';
import {Class} from '../../models/class';
import {User} from 'app/models/user';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  private notifications: Notification[];

  constructor(
    private authentication: AuthenticationService,
    private notificationsService: NotificationService
  ) { }

  ngOnInit() {
    this.notifications = [];
    this.getNotifications();
  }


  public getNotifications(): void {
    this.notificationsService.getUserNotification( this.authentication.getUserId() ).subscribe(
      result => {
        this.notifications = [];
        console.log("result");
        console.log(result);
        for ( const not of result ){
          const notification = new Notification();
          notification.id = not.id;
          notification.name = not.name;
          notification.type = not.type;
          notification.group = new Group(not.group.name);
          notification.group.id = not.group.id;
          notification.group.name = not.group.name;
          notification.group.class = new Class( not.group._class.name, not.group._class.abbreviation );
          notification.group.class.id = not.group._class.id;
          notification.group.class.user = new User(
            not.group._class.teacher.id,
            not.group._class.teacher.email,
            not.group._class.teacher.firstName,
            not.group._class.teacher.lastName,
            User._Teacher,
            ''
          );
          this.notifications.push(not);
          console.log('Notificaton');
          console.log(this.notifications);
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  public declineNotification( invitition_id: number ): void {
    this.notificationsService.declineNotification(invitition_id).subscribe(
      result => {
        this.notifications = this.notifications.filter( obj => obj.id !== invitition_id );
      },
      error => {
        console.log(error);
      }
    );
  }

  public acceptNotification( invitition_id: number ): void {
    this.notificationsService.acceptNotification(invitition_id).subscribe(
      result => {
        this.notifications = this.notifications.filter( obj => obj.id !== invitition_id );
      },
      error => {
        console.log(error);
      }
    );
  }

  public refreshNotifications(): void {
    this.getNotifications();
  }

  public getNotificationsFilter(): Notification[] {
    return this.notifications.filter( obj => obj.group );
  }

}
