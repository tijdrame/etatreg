import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BankInfosDetailComponent } from 'app/entities/bank-infos/bank-infos-detail.component';
import { BankInfos } from 'app/shared/model/bank-infos.model';

describe('Component Tests', () => {
  describe('BankInfos Management Detail Component', () => {
    let comp: BankInfosDetailComponent;
    let fixture: ComponentFixture<BankInfosDetailComponent>;
    const route = ({ data: of({ bankInfos: new BankInfos(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BankInfosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BankInfosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BankInfosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bankInfos on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bankInfos).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
