import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBp3Infos, Bp3Infos } from 'app/shared/model/bp-3-infos.model';
import { Bp3InfosService } from './bp-3-infos.service';
import { Bp3InfosComponent } from './bp-3-infos.component';
import { Bp3InfosDetailComponent } from './bp-3-infos-detail.component';
import { Bp3InfosUpdateComponent } from './bp-3-infos-update.component';

@Injectable({ providedIn: 'root' })
export class Bp3InfosResolve implements Resolve<IBp3Infos> {
  constructor(private service: Bp3InfosService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBp3Infos> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bp3Infos: HttpResponse<Bp3Infos>) => {
          if (bp3Infos.body) {
            return of(bp3Infos.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bp3Infos());
  }
}

export const bp3InfosRoute: Routes = [
  {
    path: '',
    component: Bp3InfosComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bp3Infos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: Bp3InfosDetailComponent,
    resolve: {
      bp3Infos: Bp3InfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp3Infos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: Bp3InfosUpdateComponent,
    resolve: {
      bp3Infos: Bp3InfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp3Infos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: Bp3InfosUpdateComponent,
    resolve: {
      bp3Infos: Bp3InfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp3Infos',
    },
    canActivate: [UserRouteAccessService],
  },
];
