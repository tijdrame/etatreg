import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICrpAtr, CrpAtr } from 'app/shared/model/crp-atr.model';
import { CrpAtrService } from './crp-atr.service';
import { CrpAtrComponent } from './crp-atr.component';
import { CrpAtrDetailComponent } from './crp-atr-detail.component';
import { CrpAtrUpdateComponent } from './crp-atr-update.component';

@Injectable({ providedIn: 'root' })
export class CrpAtrResolve implements Resolve<ICrpAtr> {
  constructor(private service: CrpAtrService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICrpAtr> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((crpAtr: HttpResponse<CrpAtr>) => {
          if (crpAtr.body) {
            return of(crpAtr.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CrpAtr());
  }
}

export const crpAtrRoute: Routes = [
  {
    path: '',
    component: CrpAtrComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'CrpAtrs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CrpAtrDetailComponent,
    resolve: {
      crpAtr: CrpAtrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CrpAtrs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CrpAtrUpdateComponent,
    resolve: {
      crpAtr: CrpAtrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CrpAtrs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CrpAtrUpdateComponent,
    resolve: {
      crpAtr: CrpAtrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CrpAtrs',
    },
    canActivate: [UserRouteAccessService],
  },
];
