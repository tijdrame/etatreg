import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBp2Com } from 'app/shared/model/bp-2-com.model';

type EntityResponseType = HttpResponse<IBp2Com>;
type EntityArrayResponseType = HttpResponse<IBp2Com[]>;

@Injectable({ providedIn: 'root' })
export class Bp2ComService {
  public resourceUrl = SERVER_API_URL + 'api/bp-2-coms';

  constructor(protected http: HttpClient) {}

  create(bp2Com: IBp2Com): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bp2Com);
    return this.http
      .post<IBp2Com>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bp2Com: IBp2Com): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bp2Com);
    return this.http
      .put<IBp2Com>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBp2Com>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBp2Com[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bp2Com: IBp2Com): IBp2Com {
    const copy: IBp2Com = Object.assign({}, bp2Com, {
      dou: bp2Com.dou && bp2Com.dou.isValid() ? bp2Com.dou.format(DATE_FORMAT) : undefined,
      ddd: bp2Com.ddd && bp2Com.ddd.isValid() ? bp2Com.ddd.format(DATE_FORMAT) : undefined,
      ddc: bp2Com.ddc && bp2Com.ddc.isValid() ? bp2Com.ddc.format(DATE_FORMAT) : undefined,
      dateArrete: bp2Com.dateArrete && bp2Com.dateArrete.isValid() ? bp2Com.dateArrete.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dou = res.body.dou ? moment(res.body.dou) : undefined;
      res.body.ddd = res.body.ddd ? moment(res.body.ddd) : undefined;
      res.body.ddc = res.body.ddc ? moment(res.body.ddc) : undefined;
      res.body.dateArrete = res.body.dateArrete ? moment(res.body.dateArrete) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bp2Com: IBp2Com) => {
        bp2Com.dou = bp2Com.dou ? moment(bp2Com.dou) : undefined;
        bp2Com.ddd = bp2Com.ddd ? moment(bp2Com.ddd) : undefined;
        bp2Com.ddc = bp2Com.ddc ? moment(bp2Com.ddc) : undefined;
        bp2Com.dateArrete = bp2Com.dateArrete ? moment(bp2Com.dateArrete) : undefined;
      });
    }
    return res;
  }
}
