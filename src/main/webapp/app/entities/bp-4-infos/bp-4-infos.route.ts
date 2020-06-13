import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBp4Infos, Bp4Infos } from 'app/shared/model/bp-4-infos.model';
import { Bp4InfosService } from './bp-4-infos.service';
import { Bp4InfosComponent } from './bp-4-infos.component';
import { Bp4InfosDetailComponent } from './bp-4-infos-detail.component';
import { Bp4InfosUpdateComponent } from './bp-4-infos-update.component';

@Injectable({ providedIn: 'root' })
export class Bp4InfosResolve implements Resolve<IBp4Infos> {
  constructor(private service: Bp4InfosService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBp4Infos> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bp4Infos: HttpResponse<Bp4Infos>) => {
          if (bp4Infos.body) {
            return of(bp4Infos.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bp4Infos());
  }
}

export const bp4InfosRoute: Routes = [
  {
    path: '',
    component: Bp4InfosComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bp4Infos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: Bp4InfosDetailComponent,
    resolve: {
      bp4Infos: Bp4InfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp4Infos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: Bp4InfosUpdateComponent,
    resolve: {
      bp4Infos: Bp4InfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp4Infos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: Bp4InfosUpdateComponent,
    resolve: {
      bp4Infos: Bp4InfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp4Infos',
    },
    canActivate: [UserRouteAccessService],
  },
];
