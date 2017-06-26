import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;

@Component({
  selector: 'app-group-create',
  templateUrl: './group-create.component.html',
  styleUrls: ['./group-create.component.css']
})
export class GroupCreateComponent implements OnInit, AfterViewInit {

  constructor(
    private router: Router
  ) { }

  ngOnInit() {

  }

  ngAfterViewInit(): void {
    $('.scroll').mCustomScrollbar({
      axis: 'y',
      autoHideScrollbar: true,
      scrollInertia: 20,
      advanced: {
        autoScrollOnFocus: false
      }
    }, {passive: true});
    x_navigation();
    page_content_onresize();
  }

  public refreshInvitedSudents(): void{

  }

  public saveGroup(): void {

    this.router.navigate(['/dashboard', 'classes', '1']);

  }

}
