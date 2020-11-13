import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBpsectinst, Bpsectinst } from 'app/shared/model/bpsectinst.model';
import { BpsectinstService } from './bpsectinst.service';
import { BpsectinstComponent } from './bpsectinst.component';
import { BpsectinstDetailComponent } from './bpsectinst-detail.component';
import { BpsectinstUpdateComponent } from './bpsectinst-update.component';

@Injectable({ providedIn: 'root' })
export class BpsectinstResolve implements Resolve<IBpsectinst> {
  constructor(private service: BpsectinstService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBpsectinst> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bpsectinst: HttpResponse<Bpsectinst>) => {
          if (bpsectinst.body) {
            return of(bpsectinst.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bpsectinst());
  }
}

export const bpsectinstRoute: Routes = [
  {
    path: '',
    component: BpsectinstComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bpsectinsts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BpsectinstDetailComponent,
    resolve: {
      bpsectinst: BpsectinstResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpsectinsts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BpsectinstUpdateComponent,
    resolve: {
      bpsectinst: BpsectinstResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpsectinsts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BpsectinstUpdateComponent,
    resolve: {
      bpsectinst: BpsectinstResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpsectinsts',
    },
    canActivate: [UserRouteAccessService],
  },
];
