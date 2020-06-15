import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBduDepot } from 'app/shared/model/bdu-depot.model';

type EntityResponseType = HttpResponse<IBduDepot>;
type EntityArrayResponseType = HttpResponse<IBduDepot[]>;

@Injectable({ providedIn: 'root' })
export class BduDepotService {
  public resourceUrl = SERVER_API_URL + 'api/bdu-depots';

  constructor(protected http: HttpClient) {}

  create(bduDepot: IBduDepot): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bduDepot);
    return this.http
      .post<IBduDepot>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bduDepot: IBduDepot): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bduDepot);
    return this.http
      .put<IBduDepot>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBduDepot>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBduDepot[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bduDepot: IBduDepot): IBduDepot {
    const copy: IBduDepot = Object.assign({}, bduDepot, {
      dateDepot: bduDepot.dateDepot && bduDepot.dateDepot.isValid() ? bduDepot.dateDepot.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateDepot = res.body.dateDepot ? moment(res.body.dateDepot) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bduDepot: IBduDepot) => {
        bduDepot.dateDepot = bduDepot.dateDepot ? moment(bduDepot.dateDepot) : undefined;
      });
    }
    return res;
  }
}
