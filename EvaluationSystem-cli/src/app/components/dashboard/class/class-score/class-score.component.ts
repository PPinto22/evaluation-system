import { Component, OnInit } from '@angular/core';
import {BreadCrumbService} from '../../../../services/breadcrumb.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-class-score',
  templateUrl: './class-score.component.html',
  styleUrls: ['./class-score.component.css']
})
export class ClassScoreComponent implements OnInit {

  private classId: number;

  constructor(
    private breadCrumbService: BreadCrumbService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params.subscribe( params => {
        this.classId = +params['class_id'];
        this.setBreadCrumb();
      }
    );
  }

  public setBreadCrumb(): void {
    // FIXME alterar para a forma dinamica
    this.breadCrumbService.setBreadCrum(['Classes > Arquiteturas Aplicacionais > Results' ]);
  }

}
