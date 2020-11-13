import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBpbqe, Bpbqe } from 'app/shared/model/bpbqe.model';
import { BpbqeService } from './bpbqe.service';
import { BpbqeComponent } from './bpbqe.component';
import { BpbqeDetailComponent } from './bpbqe-detail.component';
import { BpbqeUpdateComponent } from './bpbqe-update.component';

@Injectable({ providedIn: 'root' })
export class BpbqeResolve implements Resolve<IBpbqe> {
  constructor(private service: BpbqeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBpbqe> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bpbqe: HttpResponse<Bpbqe>) => {
          if (bpbqe.body) {
            return of(bpbqe.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bpbqe());
  }
}

export const bpbqeRoute: Routes = [
  {
    path: '',
    component: BpbqeComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bpbqes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BpbqeDetailComponent,
    resolve: {
      bpbqe: BpbqeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpbqes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BpbqeUpdateComponent,
    resolve: {
      bpbqe: BpbqeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpbqes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BpbqeUpdateComponent,
    resolve: {
      bpbqe: BpbqeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpbqes',
    },
    canActivate: [UserRouteAccessService],
  },
];
