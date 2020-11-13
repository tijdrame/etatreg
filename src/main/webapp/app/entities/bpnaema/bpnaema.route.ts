import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBpnaema, Bpnaema } from 'app/shared/model/bpnaema.model';
import { BpnaemaService } from './bpnaema.service';
import { BpnaemaComponent } from './bpnaema.component';
import { BpnaemaDetailComponent } from './bpnaema-detail.component';
import { BpnaemaUpdateComponent } from './bpnaema-update.component';

@Injectable({ providedIn: 'root' })
export class BpnaemaResolve implements Resolve<IBpnaema> {
  constructor(private service: BpnaemaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBpnaema> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bpnaema: HttpResponse<Bpnaema>) => {
          if (bpnaema.body) {
            return of(bpnaema.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bpnaema());
  }
}

export const bpnaemaRoute: Routes = [
  {
    path: '',
    component: BpnaemaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bpnaemas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BpnaemaDetailComponent,
    resolve: {
      bpnaema: BpnaemaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpnaemas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BpnaemaUpdateComponent,
    resolve: {
      bpnaema: BpnaemaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpnaemas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BpnaemaUpdateComponent,
    resolve: {
      bpnaema: BpnaemaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpnaemas',
    },
    canActivate: [UserRouteAccessService],
  },
];
