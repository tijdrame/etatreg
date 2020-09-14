import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CrpAtrService } from 'app/entities/crp-atr/crp-atr.service';
import { ICrpAtr, CrpAtr } from 'app/shared/model/crp-atr.model';

describe('Service Tests', () => {
  describe('CrpAtr Service', () => {
    let injector: TestBed;
    let service: CrpAtrService;
    let httpMock: HttpTestingController;
    let elemDefault: ICrpAtr;
    let expectedResult: ICrpAtr | ICrpAtr[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CrpAtrService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CrpAtr(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            demAtr: currentDate.format(DATE_FORMAT),
            datope: currentDate.format(DATE_FORMAT),
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CrpAtr', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            demAtr: currentDate.format(DATE_FORMAT),
            datope: currentDate.format(DATE_FORMAT),
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            demAtr: currentDate,
            datope: currentDate,
            dateArrete: currentDate,
          },
          returnedFromService
        );

        service.create(new CrpAtr()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CrpAtr', () => {
        const returnedFromService = Object.assign(
          {
            cenr: 'BBBBBB',
            refint: 'BBBBBB',
            num: 'BBBBBB',
            nobor: 'BBBBBB',
            typenr: 'BBBBBB',
            idAtr: 'BBBBBB',
            demAtr: currentDate.format(DATE_FORMAT),
            numAtr: 'BBBBBB',
            nomRes: 'BBBBBB',
            cpayIso: 'BBBBBB',
            datope: currentDate.format(DATE_FORMAT),
            regt: 'BBBBBB',
            nomEtr: 'BBBBBB',
            nocli: 'BBBBBB',
            catRes: 'BBBBBB',
            ceco: 'BBBBBB',
            cpayEtg: 'BBBBBB',
            natcpt: 'BBBBBB',
            sens: 'BBBBBB',
            devn: 'BBBBBB',
            mdev: 1,
            taux: 1,
            mnat: 1,
            etab: 'BBBBBB',
            nomFic: 'BBBBBB',
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            demAtr: currentDate,
            datope: currentDate,
            dateArrete: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CrpAtr', () => {
        const returnedFromService = Object.assign(
          {
            cenr: 'BBBBBB',
            refint: 'BBBBBB',
            num: 'BBBBBB',
            nobor: 'BBBBBB',
            typenr: 'BBBBBB',
            idAtr: 'BBBBBB',
            demAtr: currentDate.format(DATE_FORMAT),
            numAtr: 'BBBBBB',
            nomRes: 'BBBBBB',
            cpayIso: 'BBBBBB',
            datope: currentDate.format(DATE_FORMAT),
            regt: 'BBBBBB',
            nomEtr: 'BBBBBB',
            nocli: 'BBBBBB',
            catRes: 'BBBBBB',
            ceco: 'BBBBBB',
            cpayEtg: 'BBBBBB',
            natcpt: 'BBBBBB',
            sens: 'BBBBBB',
            devn: 'BBBBBB',
            mdev: 1,
            taux: 1,
            mnat: 1,
            etab: 'BBBBBB',
            nomFic: 'BBBBBB',
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            demAtr: currentDate,
            datope: currentDate,
            dateArrete: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CrpAtr', () => {
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
