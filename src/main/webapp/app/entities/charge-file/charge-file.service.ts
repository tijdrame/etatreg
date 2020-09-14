import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IChargeFile } from 'app/shared/model/charge-file.model';

type EntityResponseType = HttpResponse<IChargeFile>;
type EntityArrayResponseType = HttpResponse<IChargeFile[]>;

@Injectable({ providedIn: 'root' })
export class ChargeFileService {
  public resourceUrl = SERVER_API_URL + 'api/charge-files';

  constructor(protected http: HttpClient) {}

  create(chargeFile: IChargeFile): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(chargeFile);
    return this.http
      .post<IChargeFile>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(chargeFile: IChargeFile): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(chargeFile);
    return this.http
      .put<IChargeFile>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IChargeFile>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IChargeFile[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(chargeFile: IChargeFile): IChargeFile {
    const copy: IChargeFile = Object.assign({}, chargeFile, {
      dateCharge: chargeFile.dateCharge && chargeFile.dateCharge.isValid() ? chargeFile.dateCharge.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateCharge = res.body.dateCharge ? moment(res.body.dateCharge) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((chargeFile: IChargeFile) => {
        chargeFile.dateCharge = chargeFile.dateCharge ? moment(chargeFile.dateCharge) : undefined;
      });
    }
    return res;
  }
}
