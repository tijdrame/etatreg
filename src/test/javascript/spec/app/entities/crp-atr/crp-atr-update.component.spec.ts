import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { CrpAtrUpdateComponent } from 'app/entities/crp-atr/crp-atr-update.component';
import { CrpAtrService } from 'app/entities/crp-atr/crp-atr.service';
import { CrpAtr } from 'app/shared/model/crp-atr.model';

describe('Component Tests', () => {
  describe('CrpAtr Management Update Component', () => {
    let comp: CrpAtrUpdateComponent;
    let fixture: ComponentFixture<CrpAtrUpdateComponent>;
    let service: CrpAtrService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [CrpAtrUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CrpAtrUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CrpAtrUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CrpAtrService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CrpAtr(123);
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
        const entity = new CrpAtr();
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
