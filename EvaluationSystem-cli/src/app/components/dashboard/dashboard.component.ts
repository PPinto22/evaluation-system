import {AfterViewInit, Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {BreadCrumbService} from '../../services/breadcrumb.service';
import {ActivatedRoute, NavigationStart, Router} from '@angular/router';
import {GroupService} from '../../services/group.service';
import {ClassesService} from '../../services/classes.service';
import {Notification} from '../../models/notification';
import {NotificationService} from '../../services/notification.service';
import {Class} from 'app/models/class';
import {User} from '../../models/user';
import {Group} from '../../models/group';
import {ExamsService} from '../../services/exams.service';
import {Exception} from '../../execption/exception';
import {Exam} from '../../models/exam';
import 'rxjs/add/operator/filter';


declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;
declare var xn_panel_dragging: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, AfterViewInit, OnChanges, OnDestroy {

  private nameInToggleNavigation: string;
  private page_navigation_toggled: boolean;
  private collapse_struture: any;

  private notifications: Notification[];
  private examsOnGoing: Exam[];

  private intervalExamsOnGoing: any;
  private intervalNotification: any;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authentication: AuthenticationService,
    private breadCrumb: BreadCrumbService,
    private groupsService: GroupService,
    private classesService: ClassesService,
    private notificationsService: NotificationService,
    private examsService: ExamsService,
    private exception: Exception
  ) {
  }

  ngOnInit() {
    this.notifications = [];
    this.examsOnGoing = [];
    this.setNamebreadCrum();
    this.createNavbarStructure();
    this.page_navigation_toggled = false;
    this.getClasses();
    this.initExamsOnGoing();
    // TODO altualizar a navbar
    // this.router.events
    //   .filter(event => event instanceof NavigationStart)
    //   .subscribe( (event: NavigationStart) => {
    //     console.log('change router');
    //     this.getClasses();
    //   });

    this.breadCrumb.setBreadCrum(['Dashboard']);
  }

  ngOnDestroy(): void {
    clearInterval(this.intervalExamsOnGoing);
    clearInterval(this.intervalNotification);
  }

  ngAfterViewInit() {
    x_navigation();
    page_content_onresize();
    if ( this.isStudent() ) {
      this.initNotification();
    }
  }

  ngOnChanges() {
    x_navigation();
    page_content_onresize();
  }

  private initExamsOnGoing(): void {
    this.getExamsOnGoing();
    this.intervalExamsOnGoing = setInterval( () => {
      this.getExamsOnGoing();
    }, 1000 * 60);
  }
  private initNotification(): void {
    this.getNotifications();
    this.intervalNotification = setInterval( () => {
      this.getNotifications();
    }, 1000 * 60);
  }

  private getExamsOnGoing(): void {
    this.examsService.getExamsOnGoingByUserId( this.authentication.getUserId()).subscribe(
      result => {
        this.examsOnGoing = [];
        this.getOngoing(result);
        console.log('teste exames on going');
        console.log(this.examsOnGoing);
      },
      error => {
        this.exception.errorHandlingInvalidToken(error);
      }
    );
  }
  private getNotifications(): void {
    this.notificationsService.getUserNotification( this.authentication.getUserId() ).subscribe(
      result => {
        console.log(result);
        this.notifications = [];
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
          console.log(this.notifications);

        }
      },
      error => {
        console.log(error);
      }
    );
  }
  private getClasses(): void {
    this.classesService.getAllClassesByUser( this.authentication.getUserId() ).subscribe(
      result => {
        const classes_dash = this.collapse_struture[3];
        for (const resul_class of result ){
          const class_dash = classes_dash.children.find( obj => resul_class.id === obj.id );
          if (class_dash) { // já existe a class criada
            this.getGroups( class_dash, resul_class.id);
          }else { // não existe a class criada
            classes_dash.children.push( {
              level: 2,
              id: resul_class.id,
              name: resul_class.abbreviation,
              route: ['/dashboard', 'classes', resul_class.id],
              isCollapsed: false,
              children: []
            });

            this.getGroups( classes_dash.children.find( obj => resul_class.id === obj.id ), resul_class.id );

          }
        }
      },
      error => {
        console.log(error);
      }
    );
  }
  private getGroups(class_dash: any, class_id: number): void {
    this.groupsService.getGroupsClassByUser(class_id, this.authentication.getUserId()).subscribe(
      result => {
        for (const group of result) {
          const group_dash = class_dash.children.find(obj => group.id === obj.id);
          if (!group_dash) { // não existe o grupo
            class_dash.children.push({
              level: 3,
              id: group.id,
              name: group.name,
              route: ['/dashboard', 'classes', class_id, 'groups', '' + group.id],
              isCollapsed: false
            });
          }
        }
      },
      error => {
        console.log(error);
      });
  }

  public setNamebreadCrum() {
    this.breadCrumb.breadCrumDate.subscribe( value => {
      this.nameInToggleNavigation = value.pop();

      }
    );
  }

  private createNavbarStructure(): void {
    this.collapse_struture = [
      { id: 0, level: 1, name: 'Dashboard', route: ['/dashboard'], isCollapsed: false },
      { id: 1, level: 1, name: 'Schedule', route: ['/dashboard', 'schedule'], isCollapsed: false },
      { id: 2, level: 1, name: 'Results', route: ['/dashboard', 'results'], isCollapsed: false },
      { id: 3, level: 1, name: 'Classes', route: [], isCollapsed: false , children: []}
    ];
  }
  public navigateRoute(route: string[], collapse_node: any, node_ids: number[]) {

    switch ( node_ids.length ) {
      case 1: {
        this.clearCollapseLevel(1, node_ids[0]);
        this.clearCollapseLevel(2, -1);
        this.clearCollapseLevel(3, -1);
        break;
      }
      case 2: {
        this.clearCollapseLevel(1, node_ids[0]);
        this.clearCollapseLevel(2, node_ids[1]);
        this.clearCollapseLevel(3, -1);

        break;
      }
      case 3: {
        this.clearCollapseLevel(1, node_ids[0]);
        this.clearCollapseLevel(2, node_ids[1]);
        this.clearCollapseLevel(3, node_ids[2]);
        break;
      }
    }

    collapse_node.isCollapsed = true;

    if (route.length > 0 ) {
      this.router.navigate(route);
    }
  }
  public clearCollapseLevel(level: number, noclear: number) {

    switch ( level ) {
      case 1: {
        this.collapse_struture.map( obj => {
          obj.id === noclear ? obj.isCollapsed = true : obj.isCollapsed = false;
        });
        break;
      }
      case 2: {
        this.collapse_struture.map( obj_parent => {
          if ( obj_parent.children ){
            obj_parent.children.map( obj => {
              obj.id === noclear ? obj.isCollapsed = true : obj.isCollapsed = false;
            });
          }

        });
        break;
      }
      case 3: {
        this.collapse_struture.map( obj_grand => {
          if ( obj_grand.children ) {
            obj_grand.children.map(obj_parent => {
              if ( obj_parent.children ) {
                obj_parent.children.map(obj => {
                  obj.id === noclear ? obj.isCollapsed = true : obj.isCollapsed = false;
                });
              }
            });
          }
        });
        break;
      }
    }
  }

  public toggledPageNavigation(): void {
    this.page_navigation_toggled = !this.page_navigation_toggled;
  }

  public getUserName(): string {
    return this.authentication.getUserName();
  }
  public getUserType(): string {
    return this.authentication.userLogged.type;
  }

  public logout() {
    this.authentication.logout();
    this.router.navigate(['/']);
  }
  public isTeacher(): boolean {
    return this.authentication.isTeacher();
  }
  public isStudent(): boolean {
    return this.authentication.isSudent();
  }

  private getOngoing (exams): void {
    for (const exam of exams) {
      this.examsOnGoing.push(this.createExam(exam));
    }
  }
  private createExam(exam): Exam {
    const teacher = exam.group._class.teacher;
    const _teacher =  new User( teacher.id, teacher.email, teacher.firstName, teacher.lastName, teacher.type, '');
    const classe = exam.group._class;
    const _classe = new Class( classe.name, classe.abbreviation);
    _classe.id = classe.id;
    _classe.user = _teacher;
    const group = exam.group;
    const _group = new Group(group.name);
    _group.id = group.id;
    _group.class = _classe;
    const examnew = new Exam( exam.name, exam.beginDate, exam.duration);
    examnew.id = exam.id;
    examnew.group = _group;
    return examnew;
  }
}
