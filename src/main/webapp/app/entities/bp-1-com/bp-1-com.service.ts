import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBp1Com } from 'app/shared/model/bp-1-com.model';

type EntityResponseType = HttpResponse<IBp1Com>;
type EntityArrayResponseType = HttpResponse<IBp1Com[]>;

@Injectable({ providedIn: 'root' })
export class Bp1ComService {
  public resourceUrl = SERVER_API_URL + 'api/bp-1-coms';

  constructor(protected http: HttpClient) {}

  create(bp1Com: IBp1Com): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bp1Com);
    return this.http
      .post<IBp1Com>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bp1Com: IBp1Com): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bp1Com);
    return this.http
      .put<IBp1Com>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBp1Com>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBp1Com[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bp1Com: IBp1Com): IBp1Com {
    const copy: IBp1Com = Object.assign({}, bp1Com, {
      dou: bp1Com.dou && bp1Com.dou.isValid() ? bp1Com.dou.format(DATE_FORMAT) : undefined,
      ddd: bp1Com.ddd && bp1Com.ddd.isValid() ? bp1Com.ddd.format(DATE_FORMAT) : undefined,
      ddc: bp1Com.ddc && bp1Com.ddc.isValid() ? bp1Com.ddc.format(DATE_FORMAT) : undefined,
      dateArrete: bp1Com.dateArrete && bp1Com.dateArrete.isValid() ? bp1Com.dateArrete.format(DATE_FORMAT) : undefined,
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
      res.body.forEach((bp1Com: IBp1Com) => {
        bp1Com.dou = bp1Com.dou ? moment(bp1Com.dou) : undefined;
        bp1Com.ddd = bp1Com.ddd ? moment(bp1Com.ddd) : undefined;
        bp1Com.ddc = bp1Com.ddc ? moment(bp1Com.ddc) : undefined;
        bp1Com.dateArrete = bp1Com.dateArrete ? moment(bp1Com.dateArrete) : undefined;
      });
    }
    return res;
  }
}
