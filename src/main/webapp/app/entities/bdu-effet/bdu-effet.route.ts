import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBduEffet, BduEffet } from 'app/shared/model/bdu-effet.model';
import { BduEffetService } from './bdu-effet.service';
import { BduEffetComponent } from './bdu-effet.component';
import { BduEffetDetailComponent } from './bdu-effet-detail.component';
import { BduEffetUpdateComponent } from './bdu-effet-update.component';

@Injectable({ providedIn: 'root' })
export class BduEffetResolve implements Resolve<IBduEffet> {
  constructor(private service: BduEffetService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBduEffet> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bduEffet: HttpResponse<BduEffet>) => {
          if (bduEffet.body) {
            return of(bduEffet.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BduEffet());
  }
}

export const bduEffetRoute: Routes = [
  {
    path: '',
    component: BduEffetComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'BduEffets',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BduEffetDetailComponent,
    resolve: {
      bduEffet: BduEffetResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduEffets',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BduEffetUpdateComponent,
    resolve: {
      bduEffet: BduEffetResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduEffets',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BduEffetUpdateComponent,
    resolve: {
      bduEffet: BduEffetResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduEffets',
    },
    canActivate: [UserRouteAccessService],
  },
];
