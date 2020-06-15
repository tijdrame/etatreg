import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BduCdbService } from 'app/entities/bdu-cdb/bdu-cdb.service';
import { IBduCdb, BduCdb } from 'app/shared/model/bdu-cdb.model';

describe('Service Tests', () => {
  describe('BduCdb Service', () => {
    let injector: TestBed;
    let service: BduCdbService;
    let httpMock: HttpTestingController;
    let elemDefault: IBduCdb;
    let expectedResult: IBduCdb | IBduCdb[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BduCdbService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BduCdb(
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        0,
        0,
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateTraitement: currentDate.format(DATE_TIME_FORMAT),
            dateCredit: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BduCdb', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateTraitement: currentDate.format(DATE_TIME_FORMAT),
            dateCredit: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateTraitement: currentDate,
            dateCredit: currentDate,
          },
          returnedFromService
        );

        service.create(new BduCdb()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BduCdb', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            numEnreg: 'BBBBBB',
            dateTraitement: currentDate.format(DATE_TIME_FORMAT),
            typeCredit: 1,
            objetCredit: 1,
            montantCreditDemande: 1,
            dureeCreditDemande: 1,
            tauxInteretSouhaite: 'BBBBBB',
            natureDebiteur: 'BBBBBB',
            paysResidence: 1,
            ville: 1,
            statutJuridique: 1,
            sexeDebiteur: 'BBBBBB',
            secteurActivite: 'BBBBBB',
            tailleEntreprise: 'BBBBBB',
            decision: 1,
            motifRejet: 'BBBBBB',
            dateCredit: currentDate.format(DATE_FORMAT),
            montantCreditAccorde: 1,
            dureeCreditAccorde: 1,
            periodiciteRemboursement: 1,
            tauxInteretApplique: 'BBBBBB',
            montantInteret: 1,
            montantCharges: 1,
            montantCommission: 1,
            autresCommissions: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateTraitement: currentDate,
            dateCredit: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BduCdb', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            numEnreg: 'BBBBBB',
            dateTraitement: currentDate.format(DATE_TIME_FORMAT),
            typeCredit: 1,
            objetCredit: 1,
            montantCreditDemande: 1,
            dureeCreditDemande: 1,
            tauxInteretSouhaite: 'BBBBBB',
            natureDebiteur: 'BBBBBB',
            paysResidence: 1,
            ville: 1,
            statutJuridique: 1,
            sexeDebiteur: 'BBBBBB',
            secteurActivite: 'BBBBBB',
            tailleEntreprise: 'BBBBBB',
            decision: 1,
            motifRejet: 'BBBBBB',
            dateCredit: currentDate.format(DATE_FORMAT),
            montantCreditAccorde: 1,
            dureeCreditAccorde: 1,
            periodiciteRemboursement: 1,
            tauxInteretApplique: 'BBBBBB',
            montantInteret: 1,
            montantCharges: 1,
            montantCommission: 1,
            autresCommissions: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateTraitement: currentDate,
            dateCredit: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BduCdb', () => {
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
