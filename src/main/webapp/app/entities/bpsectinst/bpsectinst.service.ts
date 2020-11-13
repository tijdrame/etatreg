import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBpsectinst } from 'app/shared/model/bpsectinst.model';

type EntityResponseType = HttpResponse<IBpsectinst>;
type EntityArrayResponseType = HttpResponse<IBpsectinst[]>;

@Injectable({ providedIn: 'root' })
export class BpsectinstService {
  public resourceUrl = SERVER_API_URL + 'api/bpsectinsts';

  constructor(protected http: HttpClient) {}

  create(bpsectinst: IBpsectinst): Observable<EntityResponseType> {
    return this.http.post<IBpsectinst>(this.resourceUrl, bpsectinst, { observe: 'response' });
  }

  update(bpsectinst: IBpsectinst): Observable<EntityResponseType> {
    return this.http.put<IBpsectinst>(this.resourceUrl, bpsectinst, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBpsectinst>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBpsectinst[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
