import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBp2Com, Bp2Com } from 'app/shared/model/bp-2-com.model';
import { Bp2ComService } from './bp-2-com.service';
import { Bp2ComComponent } from './bp-2-com.component';
import { Bp2ComDetailComponent } from './bp-2-com-detail.component';
import { Bp2ComUpdateComponent } from './bp-2-com-update.component';

@Injectable({ providedIn: 'root' })
export class Bp2ComResolve implements Resolve<IBp2Com> {
  constructor(private service: Bp2ComService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBp2Com> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bp2Com: HttpResponse<Bp2Com>) => {
          if (bp2Com.body) {
            return of(bp2Com.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bp2Com());
  }
}

export const bp2ComRoute: Routes = [
  {
    path: '',
    component: Bp2ComComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bp2Coms',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: Bp2ComDetailComponent,
    resolve: {
      bp2Com: Bp2ComResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp2Coms',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: Bp2ComUpdateComponent,
    resolve: {
      bp2Com: Bp2ComResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp2Coms',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: Bp2ComUpdateComponent,
    resolve: {
      bp2Com: Bp2ComResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp2Coms',
    },
    canActivate: [UserRouteAccessService],
  },
];
