import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBp4Infos } from 'app/shared/model/bp-4-infos.model';

type EntityResponseType = HttpResponse<IBp4Infos>;
type EntityArrayResponseType = HttpResponse<IBp4Infos[]>;

@Injectable({ providedIn: 'root' })
export class Bp4InfosService {
  public resourceUrl = SERVER_API_URL + 'api/bp-4-infos';

  constructor(protected http: HttpClient) {}

  create(bp4Infos: IBp4Infos): Observable<EntityResponseType> {
    return this.http.post<IBp4Infos>(this.resourceUrl, bp4Infos, { observe: 'response' });
  }

  update(bp4Infos: IBp4Infos): Observable<EntityResponseType> {
    return this.http.put<IBp4Infos>(this.resourceUrl, bp4Infos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBp4Infos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBp4Infos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
