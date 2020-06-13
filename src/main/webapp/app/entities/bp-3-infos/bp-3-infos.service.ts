import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBp3Infos } from 'app/shared/model/bp-3-infos.model';

type EntityResponseType = HttpResponse<IBp3Infos>;
type EntityArrayResponseType = HttpResponse<IBp3Infos[]>;

@Injectable({ providedIn: 'root' })
export class Bp3InfosService {
  public resourceUrl = SERVER_API_URL + 'api/bp-3-infos';

  constructor(protected http: HttpClient) {}

  create(bp3Infos: IBp3Infos): Observable<EntityResponseType> {
    return this.http.post<IBp3Infos>(this.resourceUrl, bp3Infos, { observe: 'response' });
  }

  update(bp3Infos: IBp3Infos): Observable<EntityResponseType> {
    return this.http.put<IBp3Infos>(this.resourceUrl, bp3Infos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBp3Infos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBp3Infos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
