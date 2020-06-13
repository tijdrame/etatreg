import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { Bp2InfosService } from 'app/entities/bp-2-infos/bp-2-infos.service';
import { IBp2Infos, Bp2Infos } from 'app/shared/model/bp-2-infos.model';

describe('Service Tests', () => {
  describe('Bp2Infos Service', () => {
    let injector: TestBed;
    let service: Bp2InfosService;
    let httpMock: HttpTestingController;
    let elemDefault: IBp2Infos;
    let expectedResult: IBp2Infos | IBp2Infos[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(Bp2InfosService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Bp2Infos(0, 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, currentDate, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateChargement: currentDate.format(DATE_TIME_FORMAT),
            dateDechargement: currentDate.format(DATE_TIME_FORMAT),
            passifCliCptVue: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Bp2Infos', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateChargement: currentDate.format(DATE_TIME_FORMAT),
            dateDechargement: currentDate.format(DATE_TIME_FORMAT),
            passifCliCptVue: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateChargement: currentDate,
            dateDechargement: currentDate,
            passifCliCptVue: currentDate,
          },
          returnedFromService
        );

        service.create(new Bp2Infos()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Bp2Infos', () => {
        const returnedFromService = Object.assign(
          {
            codeIsoDevise: 'BBBBBB',
            actifBilletEtRcai: 1,
            actifMaisonMere: 1,
            actifAuTresor: 1,
            actifClientDeb: 1,
            actifEffesCpte: 1,
            actifEffetEnc: 1,
            passifMaisonMere: 1,
            passifAuTresor: 1,
            passifCliCpteCh: 1,
            passifCptApresEnc: 1,
            dateChargement: currentDate.format(DATE_TIME_FORMAT),
            dateDechargement: currentDate.format(DATE_TIME_FORMAT),
            passifCliCptVue: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateChargement: currentDate,
            dateDechargement: currentDate,
            passifCliCptVue: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Bp2Infos', () => {
        const returnedFromService = Object.assign(
          {
            codeIsoDevise: 'BBBBBB',
            actifBilletEtRcai: 1,
            actifMaisonMere: 1,
            actifAuTresor: 1,
            actifClientDeb: 1,
            actifEffesCpte: 1,
            actifEffetEnc: 1,
            passifMaisonMere: 1,
            passifAuTresor: 1,
            passifCliCpteCh: 1,
            passifCptApresEnc: 1,
            dateChargement: currentDate.format(DATE_TIME_FORMAT),
            dateDechargement: currentDate.format(DATE_TIME_FORMAT),
            passifCliCptVue: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateChargement: currentDate,
            dateDechargement: currentDate,
            passifCliCptVue: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Bp2Infos', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
