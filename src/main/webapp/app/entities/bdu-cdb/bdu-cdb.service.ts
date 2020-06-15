import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBduCdb } from 'app/shared/model/bdu-cdb.model';

type EntityResponseType = HttpResponse<IBduCdb>;
type EntityArrayResponseType = HttpResponse<IBduCdb[]>;

@Injectable({ providedIn: 'root' })
export class BduCdbService {
  public resourceUrl = SERVER_API_URL + 'api/bdu-cdbs';

  constructor(protected http: HttpClient) {}

  create(bduCdb: IBduCdb): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bduCdb);
    return this.http
      .post<IBduCdb>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bduCdb: IBduCdb): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bduCdb);
    return this.http
      .put<IBduCdb>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBduCdb>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBduCdb[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bduCdb: IBduCdb): IBduCdb {
    const copy: IBduCdb = Object.assign({}, bduCdb, {
      dateTraitement: bduCdb.dateTraitement && bduCdb.dateTraitement.isValid() ? bduCdb.dateTraitement.toJSON() : undefined,
      dateCredit: bduCdb.dateCredit && bduCdb.dateCredit.isValid() ? bduCdb.dateCredit.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateTraitement = res.body.dateTraitement ? moment(res.body.dateTraitement) : undefined;
      res.body.dateCredit = res.body.dateCredit ? moment(res.body.dateCredit) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bduCdb: IBduCdb) => {
        bduCdb.dateTraitement = bduCdb.dateTraitement ? moment(bduCdb.dateTraitement) : undefined;
        bduCdb.dateCredit = bduCdb.dateCredit ? moment(bduCdb.dateCredit) : undefined;
      });
    }
    return res;
  }
}
