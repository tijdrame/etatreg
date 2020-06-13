import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BankInfosUpdateComponent } from 'app/entities/bank-infos/bank-infos-update.component';
import { BankInfosService } from 'app/entities/bank-infos/bank-infos.service';
import { BankInfos } from 'app/shared/model/bank-infos.model';

describe('Component Tests', () => {
  describe('BankInfos Management Update Component', () => {
    let comp: BankInfosUpdateComponent;
    let fixture: ComponentFixture<BankInfosUpdateComponent>;
    let service: BankInfosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BankInfosUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BankInfosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BankInfosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BankInfosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BankInfos(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new BankInfos();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
