import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBp2Infos, Bp2Infos } from 'app/shared/model/bp-2-infos.model';
import { Bp2InfosService } from './bp-2-infos.service';
import { Bp2InfosComponent } from './bp-2-infos.component';
import { Bp2InfosDetailComponent } from './bp-2-infos-detail.component';
import { Bp2InfosUpdateComponent } from './bp-2-infos-update.component';

@Injectable({ providedIn: 'root' })
export class Bp2InfosResolve implements Resolve<IBp2Infos> {
  constructor(private service: Bp2InfosService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBp2Infos> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bp2Infos: HttpResponse<Bp2Infos>) => {
          if (bp2Infos.body) {
            return of(bp2Infos.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bp2Infos());
  }
}

export const bp2InfosRoute: Routes = [
  {
    path: '',
    component: Bp2InfosComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bp2Infos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: Bp2InfosDetailComponent,
    resolve: {
      bp2Infos: Bp2InfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp2Infos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: Bp2InfosUpdateComponent,
    resolve: {
      bp2Infos: Bp2InfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp2Infos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: Bp2InfosUpdateComponent,
    resolve: {
      bp2Infos: Bp2InfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp2Infos',
    },
    canActivate: [UserRouteAccessService],
  },
];
